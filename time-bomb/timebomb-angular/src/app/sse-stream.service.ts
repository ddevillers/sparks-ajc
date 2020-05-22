import { Injectable, NgZone } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { EventSourcePolyfill } from 'event-source-polyfill';

@Injectable({
  providedIn: 'root'
})
export class SseStreamService {
  constructor(
      private appConfig: AppConfigService,
      private zone: NgZone) { }

  public connect(onMessage: Function) {
    let url = `${ this.appConfig.url }/matches/sse-stream`;
    let eventSource = new EventSourcePolyfill(url, this.appConfig.httpOptionsSse);
    
    eventSource.onmessage = (e) => {
      this.zone.run(() => {
        console.log(e.data);
        onMessage(JSON.parse(e.data));
      });
    }

    eventSource.onerror = (e) => {
      this.connect(onMessage);
    }
  }
}