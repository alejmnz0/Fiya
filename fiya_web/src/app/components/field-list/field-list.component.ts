import { Component, Input } from '@angular/core';
import { FieldItemComponent } from "../field-item/field-item.component";
import { Field } from '../../models/field.interface';

@Component({
  selector: 'app-field-list',
  standalone: true,
  templateUrl: './field-list.component.html',
  styleUrl: './field-list.component.css',
  imports: [FieldItemComponent]
})
export class FieldListComponent {

  @Input() fieldList!: Field[];
}
