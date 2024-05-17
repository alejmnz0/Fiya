import { Component, TemplateRef } from '@angular/core';
import { FieldService } from '../../services/field.service';
import { Field } from '../../models/field.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AddField } from '../../models/add-field.interface';

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrl: './field.component.css'
})
export class FieldComponent {

  nombre: string = '';
  latitud: number = 0;
  longitud: number = 0;
  price: number = 0;
  teamCapacity: number = 0;
  ground: string = '';
  nombreErr: string = '';
  latitudErr: string = '';
  longitudErr: string = '';
  priceErr: string = '';
  teamCapacityErr: string = '';
  groundErr: string = '';
  page = 0;
  fieldList: Field[] = [];
  count = 0;
  pageSize = 0;
  groundList: String[] = ["CLAY", "NATURAL", "ARTIFICAL", "PARQUET", "CONCRETE"];

  constructor(private fieldService: FieldService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadNewPage();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
  }

  loadNewPage() {
    this.fieldService.getAllFields(this.page - 1).subscribe((fields) => {
      this.fieldList = fields.content;
      this.pageSize = fields.size;
      if (fields.totalElements > 1000) {
        this.count = 10000;
      } else {
        this.count = fields.totalElements;
      }
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
    )
  }

  crearField() {
    let newField: AddField = new AddField(this.nombre, this.latitud, this.longitud, this.price, this.teamCapacity, this.ground);
    this.fieldService.createField(newField).subscribe({
      next: resp => {
        window.location.href = "http://localhost:4200/field";
      }, error: errorG => {
        if (errorG.status = 400) {
          let errors = errorG.error.body.fields_errors;
          errors.forEach((erro: { field: any; message: any; }) => {
            switch (erro.field) {
              case "name":
                this.nombreErr = erro.message;
                break;
              case "latitude":
                this.latitudErr = erro.message;
                break;
              case "longitude":
                this.longitudErr = erro.message;
                break;
              case "price":
                this.priceErr = erro.message;
                break;
              case "teamCapacity":
                this.teamCapacityErr = erro.message;
                break;
              case "ground":
                this.groundErr = erro.message;
                break;
            }
          });
        }
      }
    });
  }

}
