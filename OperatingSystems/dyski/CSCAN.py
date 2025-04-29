import Request as R
import matplotlib.pyplot as plt

class CSCAN:
    def __init__(self, requests, head_start):
        self.curr_time = 0
        self.requests = requests
        self.head_position = head_start
        self.total_head_movement = 0
        self.sequence = [head_start]

    def simulate(self):
        completed = 0
        while completed < len(self.requests):
            available = [req for req in self.requests if req.arrival_time <= self.curr_time and not req.done]

            for req in available:
                if req.position == self.head_position:
                    req.done = True
                    completed += 1
                    self.sequence.append(req.position)

            #move in range 100

            if self.head_position < 100:
                self.curr_time += 1
                if not available:
                    go_up = False
                    continue
                self.head_position += 1
                self.total_head_movement += 1
            else:
                self.head_position = 0
                self.curr_time += 100           #range 100



    def print_results(self):
        print("\n[C-SCAN]Path:")
        print(" -> ".join(map(str, self.sequence)))
        print(f"Total head movement: {self.total_head_movement} units")

        x = list(range(len(self.sequence)))

        y = self.sequence

        plt.figure(figsize=(10, 6))
        plt.plot(x, y, color='red', marker='o', linestyle='-', linewidth=2)
        plt.title("Ruch głowicy - CSCAN", fontsize=14).set_color(color='white')
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
    request_positions = [10, 15, 5, 12, 7, 45, 63]
    request_times = [0, 572, 0, 16, 171, 0, 632]
    requests = [R.Request(pos, time) for pos, time in zip(request_positions, request_times)]

    head_start = 50

    cscan = CSCAN(requests, head_start)
    cscan.simulate()
    cscan.print_results()
