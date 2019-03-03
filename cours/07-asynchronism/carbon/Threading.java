@FunctionalInterface
public interface Runnable {
    public abstract void run();
}

new Thread(() -> {
    System.out.println("Hello from thread " + Thread.currentThread().getName());
}).start();



// création d'un executorService
ExecutorService executorService = Executors.newFixedThreadPool(2);
// exécution d'une tâche dans un thread dédié avec récupération future d'un résultat
Future<String> futureResult = executorService.submit(() -> {return "test";});
// attente de la fin de la tâche (attente blocante dans le thread "main")
String result = futureResult.get();
System.out.println(result);



ExecutorService executorService = Executors.newFixedThreadPool(2);

// création de callables
Callable<String> a = () -> "a";
Callable<String> b = () -> "b";
Callable<String> c = () -> "c";

// exécution (cet appel est bloquant jusqu'a la fin des 3 callable!)
List<Future<String>> futures = executorService.invokeAll(Arrays.asList(a, b, c));
for(Future<String> future : futures){
    System.out.println(future.get());
}


// avec un runnable
CompletableFuture.runAsync(() -> System.out.println("Hello from Thread" + Thread.currentThread().getName()));

// pour récupérer un résultat (avec un Supplier)
CompletableFuture<String> futureString = CompletableFuture.supplyAsync(() -> "Hello from Thread" + Thread.currentThread().getName());
System.out.println(futureString.get());

// pour lister la taille du pool par défaut
System.out.println(ForkJoinPool.getCommonPoolParallelism());