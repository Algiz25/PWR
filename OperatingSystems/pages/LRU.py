import matplotlib.pyplot as plt

faults_per_step = []
step_labels = []

def LRU_page_replacement(pages, frame_count):
    memory = []
    page_faults = 0
    last_used = {}

    print("Step\tPage\tMemory\tLast Used\tPage Fault")

    for step, page in enumerate(pages):
        if page not in memory:
            page_faults += 1
            if len(memory) < frame_count:
                memory.append(page)
            else:
                lru_page = min(memory, key=lambda p: last_used.get(p, -1))
                memory[memory.index(lru_page)] = page
            fault = "Yes"
        else:
            fault = "No"
        last_used[page] = step

        faults_per_step.append(page_faults)
        step_labels.append(f"{step+1}\nP{page}")
        used_info = {p: last_used[p] for p in memory}
        print(f"{step+1}\t{page}\t{memory}\t{used_info}\t{fault}")

    print(f"\nTotal Page Faults: {page_faults}")
    return page_faults

def chart(pages):
    plt.figure(figsize=(12, 6))
    plt.plot(range(len(pages)), faults_per_step, marker='o', linestyle='-', color='blue')
    plt.title('Least Recently Used').set_color(color='white')
    plt.xlabel('Step (Page Requested)').set_color(color='white')
    plt.ylabel('Cumulative Page Faults').set_color(color='white')
    plt.xticks(ticks=range(len(pages)), labels=step_labels, rotation=45, color='white')
    plt.yticks(color='white')
    plt.grid(True)
    plt.tight_layout()

    plt.gca().set_facecolor('black')
    plt.gcf().patch.set_facecolor('black')
    plt.show()


# pages = [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2]
# frame_count = 3
# LRU_page_replacement(pages, frame_count)
