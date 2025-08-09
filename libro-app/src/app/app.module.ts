import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { App } from './app';
import { ClienteComponent } from './components/cliente/cliente';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutorComponent } from './components/autor/autor';
import { LibroComponent } from './components/libro/libro';
import { CategoriaComponent } from './components/categoria/categoria';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';

@NgModule({
  declarations: [
    App,
    ClienteComponent,
    CategoriaComponent,
    AutorComponent,
    LibroComponent
  ],
  imports: [
  BrowserModule,
  AppRoutingModule,
  FormsModule,
  HttpClientModule,
  MatTableModule,
  MatPaginatorModule,
  MatSortModule,
  MatInputModule,
  MatFormFieldModule,
  MatIconModule,
  MatButtonModule,
  BrowserAnimationsModule,
  MatSelectModule,
  MatOptionModule,
  MatDialogModule,
  MatDatepickerModule,
  MatNativeDateModule
],
  providers: [
    provideBrowserGlobalErrorListeners(),
    MatDatepickerModule
      ],
  bootstrap: [App]
})
export class AppModule { }
