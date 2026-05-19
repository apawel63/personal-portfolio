import { Pipe, PipeTransform } from '@angular/core';

interface DateRange {
  startDate: string | null | undefined;
  endDate: string | null | undefined;
}

@Pipe({
  name: 'dateRange',
  standalone: true
})
export class DateRangePipe implements PipeTransform {
  transform(value: DateRange): string {
    if (!value?.startDate) return 'Present';

    const startDate = new Date(value.startDate);
    const endDate = value.endDate ? new Date(value.endDate) : null;

    const startYear = startDate.getFullYear();
    const startMonth = startDate.toLocaleString('en-US', { month: 'short' }).toUpperCase();

    if (!endDate) {
      return `${startMonth} ${startYear} - Present`;
    }

    const endYear = endDate.getFullYear();
    const endMonth = endDate.toLocaleString('en-US', { month: 'short' }).toUpperCase();

    if (startYear !== endYear) {
      // Different years: show only years
      return `${startYear} - ${endYear}`;
    } else {
      // Same year: show months followed by year
      return `${startMonth} - ${endMonth} ${startYear}`;
    }
  }
}
