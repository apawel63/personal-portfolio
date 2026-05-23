import { NgClass, NgFor, NgIf, SlicePipe } from '@angular/common';
import { Component, Input } from '@angular/core';

import { ExperienceItem } from '../portfolio-content.service';
import { DateRangePipe } from '../date-range.pipe';

@Component({
  selector: 'app-experience-section',
  standalone: true,
  imports: [NgClass, NgFor, NgIf, SlicePipe, DateRangePipe],
  templateUrl: './experience-section.component.html',
  styleUrl: './experience-section.component.css'
})
export class ExperienceSectionComponent {
  @Input() experience: ExperienceItem[] = [];
  visibleCount = 2;

  loadMore(total: number): void {
    this.visibleCount = total;
  }
}
