import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent {
  @Input() btnStyle: 'primaryt' | 'secondaryt' | 'dangert' | 'infot' | 'blurt' | 'cleart' = 'primaryt'

}
