import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'prixCategory'
})
export class PrixCategoryPipe implements PipeTransform {
  transform(value: any, ...args: any[]): any {
    if (args[0] == "label") {
      if (value < 0) {
        return "Négatif";
      }
  
      else if (value > 0) {
        return "Positif";
      }
  
      return  "Neutre";
    }

    if (value < 0) {
      return "red";
    }

    else if (value > 0) {
      return "green";
    }

    return  "blue";
  }
}