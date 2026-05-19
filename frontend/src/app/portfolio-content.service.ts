import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, forkJoin, map, of } from 'rxjs';

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

export interface PortfolioContent {
  about: AboutContent[];
  education: EducationItem[];
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

const fallbackEducation: EducationItem[] = [
  {
    id: 1,
    degree: 'Degree or Certification',
    school: 'School Name',
    location: 'City, State',
    duration: 'Year - Year'
  }
];

@Injectable({ providedIn: 'root' })
export class PortfolioContentService {
  constructor(private readonly http: HttpClient) {}

  getPortfolioContent(): Observable<PortfolioContent> {
    return forkJoin({
      about: this.http.get<AboutContent[]>('/api/about').pipe(catchError(() => of(fallbackAbout))),
      education: this.http.get<EducationItem[]>('/api/education').pipe(catchError(() => of(fallbackEducation)))
    });
  }
}
