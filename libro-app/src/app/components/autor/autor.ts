import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import Swal from 'sweetalert2';
import { Autor } from '../../model/autor.model';
import { AutorService } from '../../services/autor';

@Component({
  selector: 'app-autor',
  standalone: false,
  templateUrl: './autor.html',
  styleUrls: ['./autor.css'] // ✅ corregido: styleUrl → styleUrls
})
export class AutorComponent implements OnInit {
  autores: Autor[] = []; // ✅ nombre en minúsculas por convención
  autor: Autor = {} as Autor;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<Autor>;

  mostrarColumnas: string[] = [
    'idAutor',
    'nombre',
    'apellido',
    'pais',
    'direccion',
    'telefono',
    'correo',
    'acciones'
  ];

  @ViewChild('formularioAutor') formularioAutor!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private autorService: AutorService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.autorService.findAll().subscribe({
      next: data => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: err => {
        console.error('Error al obtener autores', err);
      }
    });
  }

  save(): void {
    this.autorService.save(this.autor).subscribe({
      next: () => {
        this.autor = {} as Autor;
        this.findAll();
      },
      error: err => {
        console.error('Error al guardar autor', err);
      }
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.autorService.update(this.idEditar, this.autor).subscribe({
        next: () => {
          this.findAll();
          this.autor = {} as Autor;
          this.editar = false;
          this.idEditar = null;
        },
        error: err => {
          console.error('Error al actualizar autor', err);
        }
      });
    }
  }

  delete(): void {
    Swal.fire({
      title: '¿Desea eliminar el autor?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then(result => {
      if (result.isConfirmed) {
        this.autorService.delete(this.autor.idAutor).subscribe({
          next: () => {
            this.findAll();
            this.autor = {} as Autor;
            Swal.fire('Eliminado', 'El autor ha sido eliminado.', 'success');
          },
          error: err => {
            console.error('Error al eliminar autor', err);
          }
        });
      } else {
        this.autor = {} as Autor;
      }
    });
  }

  editarAutor(autor: Autor): void {
    this.autor = { ...autor };
    this.idEditar = autor.idAutor;
    this.editar = true;

    setTimeout(() => {
      this.formularioAutor.nativeElement.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
      });
    }, 100);
  }

  editarAutorCancelar(form: NgForm): void {
    this.autor = {} as Autor;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarAutor(form: NgForm): void {
    if (this.editar && this.idEditar != null) {
      this.update();
    } else {
      this.save();
    }
    form.resetForm();
  }

  buscarAutor(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
}
