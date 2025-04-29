import matplotlib.pyplot as plt

faults_per_step = []
step_labels = []

def LFU_page_replacement(pages, frame_count):
    memory = []
    page_faults = 0
    curr_usages = []

    print("Step\tPage\tMemory\tUsages\tPage Fault")

    step = 0

    for page in pages:
        if page not in memory:
            page_faults += 1
            if len(memory) < frame_count:
                memory.append(page)
                curr_usages.append(1)
            else:
                least_used_index = curr_usages.index(min(curr_usages))
                memory[least_used_index] = page
                curr_usages[least_used_index] = 1
            fault = "Yes"
        else:
            curr_usages[memory.index(page)] += 1
            fault = "No"

        faults_per_step.append(page_faults)
        step_labels.append(f"{step+1}\nP{page}")
        print(f"{step+1}\t{page}\t{memory}\t{curr_usages}\t{fault}")
        step += 1

    print(f"\nTotal Page Faults: {page_faults}")
    return page_faults

def chart(pages):
    plt.figure(figsize=(12, 6))
    plt.plot(range(len(pages)), faults_per_step, marker='o', linestyle='-', color='purple')
    plt.title('Least Frequently Used').set_color(color='white')
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
# LFU_page_replacement(pages, frame_count)
