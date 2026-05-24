import { AsyncPipe, NgIf } from '@angular/common';
import { Component, OnInit, HostListener } from '@angular/core';
import { Observable } from 'rxjs';

import { PortfolioContent, PortfolioContentService } from './portfolio-content.service';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AboutSectionComponent } from './about-section/about-section.component';
import { ExperienceSectionComponent } from './experience-section/experience-section.component';
import { ProjectsSectionComponent } from './projects-section/projects-section.component';
import { EducationSectionComponent } from './education-section/education-section.component';
import { SkeletonContentComponent } from './skeleton-content/skeleton-content.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    SidebarComponent,
    AboutSectionComponent,
    ExperienceSectionComponent,
    ProjectsSectionComponent,
    EducationSectionComponent,
    SkeletonContentComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  readonly content$: Observable<PortfolioContent>;
  activeSection: string | null = 'about';

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
}
