class Request:
    def __init__(self, position, arrival_time):
        self.position = position
        self.arrival_time = arrival_time
        self.done = False