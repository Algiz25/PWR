import Request as R
import matplotlib.pyplot as plt

class SSTF:
    def __init__(self, requests, head_start):
        self.curr_time = 0
        self.requests = requests
        self.head_position = head_start
        self.total_head_movement = 0
        self.sequence = [head_start]

    def simulate(self):
        completed = 0
        is_moving = False
        current_request = None

        while completed < len(self.requests):
            available = [req for req in self.requests if req.arrival_time <= self.curr_time and not req.done]

            if is_moving and current_request:
                if self.head_position < current_request.position:
                    self.head_position += 1
                elif self.head_position > current_request.position:
                    self.head_position -= 1

                self.curr_time += 1
                self.total_head_movement += 1

                if self.head_position == current_request.position:
                    self.sequence.append(current_request.position)
                    current_request.done = True
                    completed += 1
                    is_moving = False
                    current_request = None
            else:
                if not available:
                    self.curr_time += 1
                    continue

#find closest
                closest = min(available, key=lambda req: abs(req.position - self.head_position))
                current_request = closest
                is_moving = True

    def print_results(self):
        print("\n[SSTF]Path:")
        print(" -> ".join(map(str, self.sequence)))
        print(f"Total head movement: {self.total_head_movement} units")

        x = list(range(len(self.sequence)))

        y = self.sequence

        plt.figure(figsize=(10, 6))
        plt.plot(x, y, color='red', marker='o', linestyle='-', linewidth=2)
        plt.title("Ruch głowicy - Shortest Seek Time First", fontsize=14).set_color(color='white')
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

    sstf = SSTF(requests, head_start)
    sstf.simulate()
    sstf.print_results()
