import { Component, Input, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FieldService } from '../../services/field.service';
import { Field } from '../../models/field.interface';

@Component({
  selector: 'app-field-item',
  templateUrl: './field-item.component.html',
  styleUrl: './field-item.component.css'
})
export class FieldItemComponent {
  nombre: string = '';
  latitud: string = '';
  longitud: string = '';
  price: number = 0;
  teamCapacity: number = 0;
  ground: string = '';
  nombreErr: string = '';
  latitudErr: string = '';
  longitudErr: string = '';
  priceErr: number = 0;
  teamCapacityErr: number = 0;
  groundErr: string = '';
  @Input() field!: Field;

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
}
