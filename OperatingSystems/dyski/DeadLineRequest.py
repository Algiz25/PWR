class Request:
    def __init__(self, position, arrival_time, deadline_time):
        self.position = position
        self.arrival_time = arrival_time
        self.done = False
        self.deadline_time = deadline_time