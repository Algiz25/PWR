import Request as R
import DeadLineRequest as DR
import FCFS
import SSTF
import SCAN
import CSCAN
import EDF
import FDSCAN

import matplotlib.pyplot as plt
import random

def generate_random_process(n,pos,time,deadline):
    for _ in range(n):
        pos.append(random.randint(0,100))
        t = random.randint(0,350)
        time.append(t)
        deadline.append(random.randint(t,400))


def compare(random, n):
    request_positions = []
    request_times = []
    request_deadlines = []
    if not random:
        request_positions = [10, 15, 5, 12, 7, 45, 63]
        request_times = [0, 572, 0, 16, 171, 0, 632]
        request_deadlines = [20, 600, 100, 20, 600, 1000, 100]
    else:
        generate_random_process(n,request_positions,request_times,request_deadlines)

    requests = [R.Request(pos, time) for pos, time in zip(request_positions, request_times)]

    print(request_positions)
    print(request_times)
    print(request_deadlines)

    results = {}
    head_start = 0

    fcfs = FCFS.FCFS_Scheduler(requests, head_start)
    fcfs.simulate()
    results["FCFS"] = fcfs.total_head_movement
    fcfs.print_results()

    requests = [R.Request(pos, time) for pos, time in zip(request_positions, request_times)]

    sstf = SSTF.SSTF(requests, head_start)
    sstf.simulate()
    results["SSTF"] = sstf.total_head_movement
    sstf.print_results()

    requests = [R.Request(pos, time) for pos, time in zip(request_positions, request_times)]

    scan = SCAN.SCAN(requests, head_start)
    scan.simulate()
    results["SCAN"] = scan.total_head_movement
    scan.print_results()

    requests = [R.Request(pos, time) for pos, time in zip(request_positions, request_times)]

    cscan = CSCAN.CSCAN(requests, head_start)
    cscan.simulate()
    results["CSCAN"] = cscan.total_head_movement
    cscan.print_results()

    requests = [DR.Request(pos, time, deadline) for pos, time, deadline in
                zip(request_positions, request_times, request_deadlines)]

    edf = EDF.EDF(requests, head_start)
    edf.simulate()
    results["EDF"] = edf.total_head_movement
    edf.print_results()

    requests = [DR.Request(pos, time, deadline) for pos, time, deadline in
                zip(request_positions, request_times, request_deadlines)]

    fd_scan = FDSCAN.FDSCAN(requests, head_start)
    fd_scan.simulate()
    results["FDSCAN"] = fd_scan.total_head_movement
    fd_scan.print_results()


    plt.figure(figsize=(10, 6))
    plt.bar(results.keys(), results.values(), color='red')
    plt.title("Porównanie ruchu głowicy dla różnych algorytmów dyskowych", fontsize=14).set_color(color='white')
    plt.ylabel("Ruch głowicy (ilość jednostek)", fontsize=12).set_color(color='white')
    plt.xlabel("Algorytm", fontsize=12).set_color(color='white')
    plt.grid(axis='y', linestyle='--', alpha=0.7)
    plt.tight_layout()

    plt.gca().set_facecolor('black')
    plt.gcf().patch.set_facecolor('black')
    plt.xticks(color='white')
    plt.yticks(color='white')

    plt.show()

    print(request_positions)
    print(request_times)
    print(request_deadlines)


if __name__ == '__main__':
    compare(True, 100)
