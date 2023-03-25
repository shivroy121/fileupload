import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChartsModule } from '@progress/kendo-angular-charts';
import { UploadFileComponent } from './upload-file/upload-file.component';

const routes: Routes = [
  { path: 'upload-file', component: UploadFileComponent },
  { path: '', redirectTo: 'upload-file', pathMatch: 'full' }
];

@NgModule({
  imports: [ChartsModule,RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
