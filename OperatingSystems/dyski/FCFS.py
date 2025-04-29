import Request
import matplotlib.pyplot as plt

class FCFS_Scheduler:
    def __init__(self, requests, head_start):
        self.curr_time = 0
        self.requests = requests
        self.head_position = head_start
        self.total_head_movement = 0
        self.sequence = [head_start]

    def simulate(self):
        completed = 0
        self.requests.sort(key=lambda x: x.arrival_time)
        #while completed < len(requests):
        for i in range(completed, len(self.requests)):
            #if requests[i].arrival_time <= self.curr_time:
            # time is not needed in this simulation

            head_move = abs(self.requests[i].position - self.head_position)
            # self.curr_time += head_move
            self.total_head_movement += head_move
            self.head_position = self.requests[i].position
            self.sequence.append(self.requests[i].position)


    def print_results(self):
        print("\n[FCFS]Path:")
        print(" -> ".join(map(str, self.sequence)))
        print(f"Total head movement: {self.total_head_movement} units")

        x = list(range(len(self.sequence)))

        y = self.sequence

        plt.figure(figsize=(10, 6))
        plt.plot(x, y, color='red', marker='o', linestyle='-', linewidth=2)
        plt.title("Ruch głowicy - First Come First Serve", fontsize=14).set_color(color='white')
        plt.xlabel("Krok", fontsize=12).set_color(color='white')
        plt.ylabel("Pozycja głowicy", fontsize=12).set_color(color='white')
        plt.grid(True, linestyle='--', alpha=0.5)
        plt.tight_layout()

        plt.gca().set_facecolor('black')
        plt.gcf().patch.set_facecolor('black')
        plt.xticks(color='white')
        plt.yticks(color='white')

        plt.show()


if __name__ == "__main__":
    request_positions = [10, 15, 5, 12, 7, 12, 63]
    request_times = [190, 572, 156, 16, 171, 0, 632]
    requests = []
    for i in range(len(request_positions)):
        req = Request.Request(request_positions[i],request_times[i])
        requests.append(req)

    head_start = 50

    fcfs = FCFS_Scheduler(requests, head_start)
    fcfs.simulate()
    fcfs.print_results()