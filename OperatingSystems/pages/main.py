import random
import FIFO
import LFU
import LRU
import LRUA
import RAND
import OPT
import matplotlib.pyplot as plt

def get_random_data(len):
    arr = []
    for i in range(len):
        arr.append(random.randint(0,9))
    return arr


if __name__ == '__main__':
    pages = get_random_data(10)
    frame_count = 3

    fifo = FIFO.FIFO_page_replacement(pages,frame_count)
    FIFO.chart(pages)
    lfu = LFU.LFU_page_replacement(pages,frame_count)
    LFU.chart(pages)
    opt = OPT.optimal_page_replacement(pages,frame_count)
    OPT.chart(pages)
    lru = LRU.LRU_page_replacement(pages,frame_count)
    LRU.chart(pages)
    lrua = LRUA.LRUA_page_replacement(pages,frame_count)
    LRUA.chart(pages)
    rand = RAND.RAND_page_replacement(pages,frame_count)
    RAND.chart(pages)


    labels = ['FIFO','OPT', 'LFU', 'LRU', 'LRU-Approx', 'RAND']
    values = [fifo, opt, lfu, lru, lrua, rand]

    plt.figure(figsize=(10, 6))
    bars = plt.bar(labels, values, color=['red'])
    plt.title("Page Replacement Algorithms – Total Page Faults").set_color('white')
    plt.ylabel("Total Page Faults").set_color('white')
    plt.grid(axis='y')

    plt.xticks(color='white')
    plt.yticks(color='white')
    plt.gca().set_facecolor('black')
    plt.gcf().patch.set_facecolor('black')

    plt.tight_layout()
    plt.show()
