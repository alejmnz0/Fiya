import { Component, Input, TemplateRef } from '@angular/core';
import { Field } from '../../models/field.interface';
import { FieldService } from '../../services/field.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EditField } from '../../models/edit-field.interface';

@Component({
  selector: 'app-field-item',
  templateUrl: './field-item.component.html',
  styleUrl: './field-item.component.css'
})
export class FieldItemComponent {
  nombre: string = '';
  latitud: number = 0;
  longitud: number = 0;
  price: number = 0;
  teamCapacity: number = 0;
  ground: string = '';
  description: string = '';
  nombreErr: string = '';
  latitudErr: string = '';
  longitudErr: string = '';
  priceErr: string = '';
  teamCapacityErr: string = '';
  groundErr: string = '';
  descriptionErr: string = '';
  @Input() field!: Field;
  groundList: String[] = ["CLAY", "NATURAL", "ARTIFICAL", "PARQUET", "CONCRETE"];


  constructor(private modalService: NgbModal, private fieldService: FieldService) { }

  popoverClicked(event: MouseEvent) {
    event.stopPropagation();
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content);
    this.nombre = this.field.name;
    this.latitud = this.field.latitude;
    this.longitud = this.field.longitude;
    this.price = this.field.price;
    this.teamCapacity = this.field.teamCapacity;
    this.ground = this.field.ground;
  }

  delete() {
    this.fieldService.deleteField(this.field.id).subscribe(ans => {
      window.location.reload();
    });
  }

  editField() {
    let newField: EditField = new EditField(this.nombre, this.latitud, this.longitud, this.price, this.teamCapacity, this.ground, this.description);
    this.fieldService.editField(this.field.id, newField).subscribe({
      next: data => {
        this.modalService.dismissAll();
        window.location.reload();
      },

      error: errorG => {
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
              case "description":
                this.descriptionErr = erro.message;
                break;
            }
          });
        }
      }
    })
  }
}
