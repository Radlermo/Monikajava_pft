package pl.stqa.pft.sandbox;

/* test czy liczba jest liczba pierwsza*/
public class Primes {
  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i++) {
      /* liczba pierwsza to taka która dzieli się przez jeden i przez samą siebie.
      int i=2 - licznik zaczyna się od 2
         i<n; wskazujemy miejsce gdzie licznik powinien się zatrzymać
         i= i+1 lub i+=1 lub i++  - zmienna licznika na każdej iteracji cyklu*/
      /* ciało cyklu czyli działania które powinny się odbywać w każdej iteracji*/
      if (n % i == 0) {   /* jeżeli reszta z dzielenia n/i =0 to oznacza liczba n nie jest pierwsza*/
    return false;
          }
    }
    return true;
  }
 /* bardziej optymalny test niż powyżej */
  public static boolean isPrimeFast(int n) {
    for (int i = 2; i < n/2; i++) {
            if (n % i == 0) {   /* jeżeli reszta z dzielenia n/i =0 to oznacza liczba n nie jest pierwsza*/
        return false;
      }
    }
    return true;
  }

  /* jeszcze szybsza funkcja   */
  public static boolean isPrimeFast2(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      if (n % i == 0) {   /* jeżeli reszta z dzielenia n/i =0 to oznacza liczba n nie jest pierwsza*/
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
      int i = 2;
      while (i < n) {
        if (n % i == 0) {   /* jeżeli reszta z dzielenia n/i =0 to oznacza liczba n nie jest pierwsza*/
          return false;
        }
        i++; /* cykl */
      }
      return true;
    }

  public static boolean isPrimeWhile2(int n) {
    int i = 2;
    while (i < n && n % i != 0) {
      i++; /* kolejne liczby cyklu */
    }
    return i == n;
  }

  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i++) {
      if (n % i == 0) {   /* jeżeli reszta z dzielenia n/i =0 to oznacza liczba n nie jest pierwsza*/
        return false;
      }
    }
    return true;
  }
}
