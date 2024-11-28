import { Routes } from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import { TestComponent } from "./test/test.component";
import { FormComponent } from "./form/form.component";
import { InfoComponent } from "./info/info.component"
import { SearchComponent} from './search/search.component';
import { MeasurementResultsComponent} from './measurement-results/measurement-results.component';

export const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'test', component: TestComponent},
  {path: 'form', component: FormComponent},
  {path: 'info', component: InfoComponent},
  {path: 'search', component: SearchComponent},
  {path: 'measurement-results', component: MeasurementResultsComponent}
];


