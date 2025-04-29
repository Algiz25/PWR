import matplotlib.pyplot as plt

faults_per_step = []
step_labels = []

def LRUA_page_replacement(pages, frame_count):
    memory = [-1] * frame_count
    reference_bits = [0] * frame_count
    pointer = 0
    page_faults = 0

    print("Step\tPage\tMemory\tRef Bits\tPointer\tPage Fault")

    for step, page in enumerate(pages):
        fault = "No"

        if page in memory:
            reference_bits[memory.index(page)] = 1
        else:
            page_faults += 1
            fault = "Yes"
            while True:
                if reference_bits[pointer] == 0:
                    memory[pointer] = page
                    reference_bits[pointer] = 1
                    pointer = (pointer + 1) % frame_count
                    break
                else:
                    reference_bits[pointer] = 0
                    pointer = (pointer + 1) % frame_count

        faults_per_step.append(page_faults)
        step_labels.append(f"{step+1}\nP{page}")
        print(f"{step+1}\t{page}\t{memory}\t{reference_bits}\t{pointer}\t{fault}")

    print(f"\nTotal Page Faults: {page_faults}")
    return page_faults

def chart(pages):
    plt.figure(figsize=(12, 6))
    plt.plot(range(len(pages)), faults_per_step, marker='o', linestyle='-', color='green')
    plt.title('LRU Approximation').set_color(color='white')
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
# LRUA_page_replacement(pages, frame_count)
