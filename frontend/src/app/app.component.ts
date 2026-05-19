import { AsyncPipe, NgFor, NgIf, NgClass, SlicePipe } from '@angular/common';
import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { Observable } from 'rxjs';

import { PortfolioContent, PortfolioContentService } from './portfolio-content.service';
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
    // cleanup if needed
  }

  getFaClass(icon: { library: string; name: string } | null | undefined): string {
    if (!icon || !icon.name) {
      return 'fa-solid fa-circle';
    }

    const mapping: Record<string, string> = {
      MdSchool: 'fa-solid fa-graduation-cap',
      SiGeneralmotors: 'fa-solid fa-industry',
      GiArchiveResearch: 'fa-solid fa-book-open',
      MdSportsHockey: 'fa-solid fa-hockey-puck'
    };

    return mapping[icon.name] ?? 'fa-solid fa-circle';
  }
}
