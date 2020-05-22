import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'matchState'
})
export class MatchStatePipe implements PipeTransform {
  transform(value: any): string {
    switch (value) {
      case 'WAITING': return "En attente";
      case 'READY': return "Prêt";
      case 'PLAYING': return "En cours";
      case 'TERMINATED': return "Terminé";
    }
  }
}
