import { NgClass, NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';

import { SkillItem } from '../portfolio-content.service';

@Component({
  selector: 'app-about-section',
  standalone: true,
  imports: [NgClass, NgFor],
  templateUrl: './about-section.component.html',
  styleUrl: './about-section.component.css'
})
export class AboutSectionComponent {
  @Input() skills: SkillItem[] = [];

  getCategoryClass(category: string): string {
    return 'skill-cat-' + category.toLowerCase().replace(/[^a-z0-9]+/g, '-');
  }
}
