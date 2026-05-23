import { NgFor, NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';

import { EducationItem } from '../portfolio-content.service';

@Component({
  selector: 'app-education-section',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './education-section.component.html',
  styleUrl: './education-section.component.css'
})
export class EducationSectionComponent {
  @Input() education: EducationItem[] = [];
}
