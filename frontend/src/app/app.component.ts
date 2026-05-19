import { AsyncPipe, NgFor, NgIf, NgClass, SlicePipe } from '@angular/common';
import { Component } from '@angular/core';
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
export class AppComponent {
  readonly content$: Observable<PortfolioContent>;

  constructor(portfolioContentService: PortfolioContentService) {
    this.content$ = portfolioContentService.getPortfolioContent();
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
