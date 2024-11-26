import { Routes } from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import { TestComponent } from "./test/test.component";
import { FormComponent } from "./form/form.component";

export const routes: Routes = [
    {path: '', component: LandingComponent},
    {path: 'test', component: TestComponent},
    {path: 'form', component: FormComponent}
];


