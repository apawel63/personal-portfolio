import { AsyncPipe, NgFor, NgIf, NgClass, SlicePipe } from '@angular/common';
import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { Observable } from 'rxjs';

import { PortfolioContent, PortfolioContentService, ProjectItem, SkillItem } from './portfolio-content.service';
import { DateRangePipe } from './date-range.pipe';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AsyncPipe, NgFor, NgIf, NgClass, SlicePipe, DateRangePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {
  readonly content$: Observable<PortfolioContent>;
  activeSection: string | null = 'about';
  selectedProject: ProjectItem | null = null;

  constructor(portfolioContentService: PortfolioContentService) {
    this.content$ = portfolioContentService.getPortfolioContent();
  }

  ngOnInit(): void {
    setTimeout(() => this.updateActiveSection(), 100);
  }

  @HostListener('window:scroll')
  onScroll(): void {
    this.updateActiveSection();
  }

  private updateActiveSection(): void {
    const sections = document.querySelectorAll('.section-block');
    let current = 'about';

    const atBottom = window.scrollY + window.innerHeight >= document.documentElement.scrollHeight - 100;

    if (atBottom && sections.length > 0) {
      current = sections[sections.length - 1].id;
    } else {
      sections.forEach(section => {
        const sectionTop = section.getBoundingClientRect().top;
        if (sectionTop <= 150) {
          current = section.id;
        }
      });
    }

    this.activeSection = current;
  }

  ngOnDestroy(): void {
    document.body.classList.remove('modal-open');
  }

  openProjectDetails(project: ProjectItem): void {
    this.selectedProject = project;
    document.body.classList.add('modal-open');
  }

  closeProjectDetails(): void {
    this.selectedProject = null;
    document.body.classList.remove('modal-open');
  }

  @HostListener('document:keydown.escape')
  onEscape(): void {
    if (this.selectedProject) {
      this.closeProjectDetails();
    }
  }

  // TODO: Refactor icon mappings - should be completely dynamic from DB
  getFaClass(icon: { library: string; name: string } | null | undefined): string {
    if (!icon || !icon.name) {
      return 'fa-solid fa-circle';
    }

    const mapping: Record<string, string> = {
      FaExternalLinkAlt: 'fa-solid fa-arrow-up-right-from-square',
      FaFilePdf: 'fa-solid fa-file-pdf',
      FaGithub: 'fa-brands fa-github',
      FaGlobe: 'fa-solid fa-globe',
      FaLinkedin: 'fa-brands fa-linkedin',
      FaYoutube: 'fa-brands fa-youtube',
      SiAndroid: 'fa-brands fa-android',
      SiCss3: 'fa-brands fa-css3-alt',
      SiExpress: 'fa-solid fa-server',
      SiFirebase: 'fa-solid fa-fire',
      SiGit: 'fa-brands fa-git-alt',
      SiGithub: 'fa-brands fa-github',
      SiHtml5: 'fa-brands fa-html5',
      SiJava: 'fa-brands fa-java',
      SiJavascript: 'fa-brands fa-js',
      SiMicrosoftazure: 'fa-brands fa-microsoft',
      SiMongodb: 'fa-solid fa-leaf',
      SiNextdotjs: 'fa-solid fa-n',
      SiNodedotjs: 'fa-brands fa-node-js',
      SiPython: 'fa-brands fa-python',
      SiReact: 'fa-brands fa-react',
      SiSass: 'fa-brands fa-sass',
      SiSpringboot: 'fa-solid fa-leaf',
      MdSchool: 'fa-solid fa-graduation-cap',
      SiGeneralmotors: 'fa-solid fa-industry',
      GiArchiveResearch: 'fa-solid fa-book-open',
      MdSportsHockey: 'fa-solid fa-hockey-puck'
    };

    return mapping[icon.name] ?? 'fa-solid fa-circle';
  }

  getSkillFaClass(skill: SkillItem): string {
    const mapping: Record<string, string> = {
      'Java': 'fa-brands fa-java',
      'Spring Boot': 'fa-solid fa-leaf',
      'Angular': 'fa-brands fa-angular',
      'React Native': 'fa-brands fa-react',
      'Azure': 'fa-brands fa-microsoft',
      'GitHub Actions': 'fa-brands fa-github',
      'Kubernetes': 'fa-solid fa-dharmachakra',
      'Docker': 'fa-brands fa-docker',
      'Python': 'fa-brands fa-python',
      'LangChain & LangGraph': 'fa-solid fa-bezier-curve',
      'OpenAI APIs': 'fa-solid fa-robot',
      'SQL Databases': 'fa-solid fa-database',
    };

    return mapping[skill.name] ?? this.getFaClass(skill.icon);
  }
}
