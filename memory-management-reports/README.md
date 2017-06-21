## Reports collected using VisualVM tool
### Out-of-memory app

**Defaults:** Java 7, max available memory: 2048 MB.

- [Default settings - memory graph](img/out_of_memory_memory_default.png)
- [Default settings - thread graph](img/out_of_memory_threads_default.png)
- [Default settings - memory profiling](img/out_of_memory_memory_profiling.png)
- [New generation max size increased to 1512MB - memory graph](img/out_of_memory_memory_new_size_increased.png)
- [New generation max size increased to 1512MB - thread graph](img/out_of_memory_threads_new_size_increased.png)
- [New generation max size decreased to to 256MB - memory graph](img/out_of_memory_momory_new_size_decreased.png)
- [New generation max size decreased to to 256MB - thread graph](img/out_of_memory_threads_new_size_decreased.png)
- [Default settings with G1GC - memory graph](img/out_of_memory_memory_G1_default.png)
- [G1GC - max pause for GC call set to 100ms - memory graph](img/out_of_memory_memory_G1_100ms_pause.png)
- [G1GC - max pause for GC call set to 10s (10000ms) - memory graph](img/out_of_memory_memory_G1_10s_pause.png)
- [G1GC - survivor ratio set to 1 (default is 8) - memory graph](img/out_of_memory_memory_G1_survivorRatio_1.png)
- [G1GC - tenuring treahold set to 1 (default is 15) - memory graph](img/out_of_memory_memory_G1_tenuringTreshold_1.png)

### Banking app

**Defaults:** Java 8.

 - [Banking app idle - memory graph](img/BankingApp_idle_memory.png)
 - [Banking app idle - thread graph](img/BankingApp_idle_threads.png)
 - [Banking app idle - memory profiling](img/BankingApp_idle_profiler.png)
 - [Banking app running - memory graph](img/BankingApp_active_memory.png)
 - [Banking app running - thread graph](img/BankingApp_active_threads.png)
