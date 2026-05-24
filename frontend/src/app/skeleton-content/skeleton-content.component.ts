import { Component } from '@angular/core';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-skeleton-content',
  standalone: true,
  imports: [NgFor],
  templateUrl: './skeleton-content.component.html',
  styleUrl: './skeleton-content.component.css'
})
export class SkeletonContentComponent {
  skillCount = Array(12);
  experienceCount = Array(3);
  taskCount = Array(3);
  pillCount = Array(4);
  projectCount = Array(2);
  educationCount = Array(2);
}
