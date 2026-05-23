import { NgClass, NgFor, NgIf, SlicePipe } from '@angular/common';
import { Component, Input } from '@angular/core';

import { ProjectItem } from '../portfolio-content.service';

@Component({
  selector: 'app-projects-section',
  standalone: true,
  imports: [NgClass, NgFor, NgIf, SlicePipe],
  templateUrl: './projects-section.component.html',
  styleUrl: './projects-section.component.css'
})
export class ProjectsSectionComponent {
  @Input() projects: ProjectItem[] = [];
  visibleCount = 3;
  expandedDescriptions = new Set<number>();

  loadMore(total: number): void {
    this.visibleCount = total;
  }

  toggleDescription(projectId: number): void {
    if (this.expandedDescriptions.has(projectId)) {
      this.expandedDescriptions.delete(projectId);
    } else {
      this.expandedDescriptions.add(projectId);
    }
  }

  isDescriptionTruncatable(description: string): boolean {
    return description.length > 225;
  }
}
