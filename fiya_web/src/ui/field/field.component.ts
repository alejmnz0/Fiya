import { Component } from '@angular/core';
import { FieldService } from '../../services/field.service';
import { Field } from '../../models/field.interface';
import { FieldListComponent } from "../../components/field-list/field-list.component";
import { NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-field',
  standalone: true,
  templateUrl: './field.component.html',
  styleUrl: './field.component.css',
  imports: [FieldListComponent]
})
export class FieldComponent {

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
  page = 0;
  fieldList: Field[] = [];
  count = 0;
  pageSize = 0;

  constructor(private fieldService: FieldService) { }

  ngOnInit(): void {
    this.loadNewPage();
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

}
