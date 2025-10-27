import {
  ApplicationConfig,
  inject,
  provideAppInitializer,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { credentialsInterceptor } from './modules/shared/interceptors/credentials-interceptor';
import { AccountService } from './modules/shared/service/account-service';
import { lastValueFrom } from 'rxjs';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      // withFetch(),
      withInterceptors([credentialsInterceptor]),
    ),
    provideAppInitializer(async () => {
      const accountService = inject(AccountService);

      return new Promise<void>(async (resolve) => {
        try {
          await lastValueFrom(accountService.init());
        } catch (error) {
          console.error('Error during app initialization:', error);
        } finally {
          resolve();
        }
      });
    }),
  ],
};
