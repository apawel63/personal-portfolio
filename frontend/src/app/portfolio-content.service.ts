import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, forkJoin, of, timeout } from 'rxjs';

export interface IconContent {
  id: number;
  library: string;
  name: string;
}

export interface AboutContent {
  id: number;
  heading: string;
  subheading?: string | null;
  icon?: IconContent | null;
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

export interface ExperienceItem {
  id: number;
  company: string;
  location?: string | null;
  title: string;
  startDate: string;
  endDate?: string | null;
  tasks: ExperienceTask[];
}

export interface ProjectTechnology {
  id: number;
  name: string;
  icon?: IconContent | null;
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

export interface PortfolioContent {
  about: AboutContent[];
  experience: ExperienceItem[];
  education: EducationItem[];
  projects: ProjectItem[];
}

const fallbackAbout: AboutContent[] = [
  {
    id: 1,
    heading: 'About',
    subheading: 'A short snapshot of the person behind the work.',
    icon: {
      id: 1,
      library: 'default',
      name: 'portfolio'
    }
  }
];

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
    ]
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
        icon: { id: 1, library: 'fa', name: 'FaGithub' },
        url: 'https://github.com/'
      }
    ]
  }
];

@Injectable({ providedIn: 'root' })
export class PortfolioContentService {
  constructor(private readonly http: HttpClient) {}

  getPortfolioContent(): Observable<PortfolioContent> {
    return forkJoin({
      about: this.http.get<AboutContent[]>('/api/about').pipe(timeout(3000), catchError(() => of(fallbackAbout))),
      experience: this.http.get<ExperienceItem[]>('/api/work-experience').pipe(timeout(3000), catchError(() => of(fallbackExperience))),
      education: this.http.get<EducationItem[]>('/api/education').pipe(timeout(3000), catchError(() => of(fallbackEducation))),
      projects: this.http.get<ProjectItem[]>('/api/projects').pipe(timeout(3000), catchError(() => of(fallbackProjects)))
    });
  }
}
