import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable, catchError, forkJoin, map, of, timeout } from 'rxjs';

export interface IconContent {
  cssClass: string;
}

export interface EducationItem {
  id: number;
  degree: string;
  school: string;
  location?: string | null;
  duration?: string | null;
  avatar?: string | null;
}

export interface ExperienceTask {
  id: number;
  description: string;
  sortOrder: number;
}

export interface ExperienceTechnology {
  id: number;
  name: string;
  icon?: IconContent | null;
  url?: string | null;
}

export interface ExperienceItem {
  id: number;
  company: string;
  location?: string | null;
  title: string;
  startDate: string;
  endDate?: string | null;
  tasks: ExperienceTask[];
  technologies: ExperienceTechnology[];
}

export interface ProjectTechnology {
  id: number;
  name: string;
  icon?: IconContent | null;
  url?: string | null;
}

export interface ProjectLink {
  id: number;
  name: string;
  icon?: IconContent | null;
  url: string;
}

export interface ProjectItem {
  id: number;
  title: string;
  category: string;
  description: string;
  imageUrl: string;
  technologies: ProjectTechnology[];
  links: ProjectLink[];
}

export interface SkillItem {
  id: number;
  category: string;
  name: string;
  icon?: IconContent | null;
  displayOrder: number;
}

export interface PortfolioContent {
  skills: SkillItem[];
  experience: ExperienceItem[];
  education: EducationItem[];
  projects: ProjectItem[];
}

const fallbackExperience: ExperienceItem[] = [
  {
    id: 1,
    company: 'Company Name',
    title: 'Job Title',
    location: 'City, State',
    startDate: '2024-01-01',
    endDate: null,
    tasks: [
      { id: 1, description: 'Task or responsibility', sortOrder: 1 }
    ],
    technologies: []
  }
];

const fallbackEducation: EducationItem[] = [
  {
    id: 1,
    degree: 'Degree or Certification',
    school: 'School Name',
    location: 'City, State',
    duration: 'Year - Year'
  }
];

const fallbackProjects: ProjectItem[] = [
  {
    id: 1,
    title: 'Portfolio Project',
    category: 'Web Development',
    description: 'A featured project will appear here once the projects API is available.',
    imageUrl: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?auto=format&fit=crop&w=900&q=80',
    technologies: [
      { id: 1, name: 'Angular' },
      { id: 2, name: 'Spring Boot' }
    ],
    links: [
      {
        id: 1,
        name: 'Source Code',
        icon: { cssClass: 'fa-brands fa-github' },
        url: 'https://github.com/'
      }
    ]
  }
];

const fallbackSkills: SkillItem[] = [
  { id: 1, category: 'Frontend', name: 'HTML', icon: { cssClass: 'fa-brands fa-html5' }, displayOrder: 1 },
  { id: 2, category: 'Frontend', name: 'CSS', icon: { cssClass: 'fa-brands fa-css3-alt' }, displayOrder: 2 },
  { id: 3, category: 'Frontend', name: 'JavaScript', icon: { cssClass: 'fa-brands fa-js' }, displayOrder: 3 },
  { id: 4, category: 'Frontend', name: 'React', icon: { cssClass: 'fa-brands fa-react' }, displayOrder: 4 },
  { id: 5, category: 'Backend', name: 'Spring Boot', icon: { cssClass: 'fa-solid fa-leaf' }, displayOrder: 5 },
  { id: 6, category: 'Language', name: 'Python', icon: { cssClass: 'fa-brands fa-python' }, displayOrder: 6 },
  { id: 7, category: 'Cloud', name: 'Azure', icon: { cssClass: 'fa-brands fa-microsoft' }, displayOrder: 7 },
  { id: 8, category: 'Tools', name: 'Git', icon: { cssClass: 'fa-brands fa-git-alt' }, displayOrder: 8 }
];

@Injectable({ providedIn: 'root' })
export class PortfolioContentService {
  constructor(private readonly http: HttpClient) {}

  getPortfolioContent(): Observable<PortfolioContent> {
    return forkJoin({
      skills: this.http.get<SkillItem[]>(`${environment.apiBaseUrl}/api/skills`).pipe(
        timeout(30000),
        catchError(() => of(fallbackSkills)),
        map(skills => [...skills].sort((a, b) => b.category.localeCompare(a.category) || a.displayOrder - b.displayOrder)) 
      ),
      experience: this.http.get<ExperienceItem[]>(`${environment.apiBaseUrl}/api/work-experience`).pipe(timeout(30000), catchError(() => of(fallbackExperience))),
      education: this.http.get<EducationItem[]>(`${environment.apiBaseUrl}/api/education`).pipe(timeout(30000), catchError(() => of(fallbackEducation))),
      projects: this.http.get<ProjectItem[]>(`${environment.apiBaseUrl}/api/projects`).pipe(timeout(30000), catchError(() => of(fallbackProjects)))
    });
  }
}
