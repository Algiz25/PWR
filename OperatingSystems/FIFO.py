import random

def generate_random_process():
    if len(process_id) == 0:
        process_id.append(1)
    else:
        process_id.append(process_id[-1] + 1)
    burst_time.append(random.randint(1, 10))

def print_results(waiting_time, turnaround_time):
    print("ID " + "|" + " Burst Time " + "|" + " Waiting time " + "|" + " Turn around time")
    for i in range(num_processes):
        print(f"{process_id[i]:2} |   {burst_time[i]:8} |   {waiting_time[i]:8}   |   {turnaround_time[i]:7}")

def calculate_average_time():
    waiting_time = [0] * num_processes
    turnaround_time = [0] * num_processes

    waiting_time[0] = 0
    for j in range(1, num_processes):
        waiting_time[j] = burst_time[j - 1] + waiting_time[j - 1]

    for j in range(num_processes):
        turnaround_time[i] = burst_time[i] + waiting_time[i]

    print_results(waiting_time, turnaround_time)
    print("Average waiting time: " + str(sum(waiting_time)))
    print("Average turn around time: " + str(sum(turnaround_time)))


if __name__ == "__main__":
    num_processes = 5
    process_id = []
    burst_time = []

    for i in range(num_processes):
        generate_random_process()

    calculate_average_time()

