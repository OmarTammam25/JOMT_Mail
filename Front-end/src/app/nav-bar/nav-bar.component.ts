import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  sortingCategories: string[] = []

  @Output() sortingActions = new EventEmitter<string>();
  emitSortingAction(type: string){
    this.sortingActions.emit(type);
  }

  @Output() search = new EventEmitter<string>();
  emitSearch(keyword: string){
    this.search.emit(keyword);
  }
}
