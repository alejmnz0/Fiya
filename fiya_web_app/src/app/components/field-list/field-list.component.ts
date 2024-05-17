import { Component, Input } from '@angular/core';
import { Field } from '../../models/field.interface';

@Component({
  selector: 'app-field-list',
  templateUrl: './field-list.component.html',
  styleUrl: './field-list.component.css'
})
export class FieldListComponent {
  @Input() fieldList!: Field[];
}
