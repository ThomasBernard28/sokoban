import random as rd
from functions import rotation


class PatternGenerator:
    empty = [
        ['e', 'e', 'e'],
        ['e', 'e', 'e'],
        ['e', 'e', 'e']
    ]
    square = [
        ['w', 'w', 'e'],
        ['w', 'w', 'e'],
        ['e', 'e', 'e']
    ]
    corner_2 = [
        ['w', 'w', 'e'],
        ['e', 'e', 'e'],
        ['e', 'e', 'e']
    ]
    center_line = [
        ['e', 'w', 'e'],
        ['e', 'w', 'e'],
        ['e', 'w', 'e']
    ]
    center_point = [
        ['e', 'e', 'e'],
        ['e', 'w', 'e'],
        ['e', 'e', 'e']
    ]
    top_point = [
        ['e', 'w', 'e'],
        ['e', 'e', 'e'],
        ['e', 'e', 'e']
    ]
    corner_point = [
        ['w', 'e', 'e'],
        ['e', 'e', 'e'],
        ['e', 'e', 'e']
    ]
    side_line = [
        ['w', 'e', 'e'],
        ['w', 'e', 'e'],
        ['w', 'e', 'e']
    ]
    corner_3 = [
        ['w', 'w', 'e'],
        ['w', 'e', 'e'],
        ['e', 'e', 'e']
    ]
    anti_corner = [
        ['e', 'w', 'e'],
        ['w', 'w', 'e'],
        ['e', 'e', 'e']
    ]
    cross = [
        ['e', 'w', 'e'],
        ['w', 'w', 'w'],
        ['e', 'w', 'e']
    ]
    double_line = [
        ['w', 'w', 'w'],
        ['e', 'e', 'e'],
        ['w', 'w', 'w']
    ]
    double_side_point = [
        ['w', 'e', 'e'],
        ['e', 'e', 'e'],
        ['w', 'e', 'e']
    ]

    pattern_collection = dict()
    pattern_collection["empty"] = empty
    pattern_collection["square"] = square
    pattern_collection["corner_2"] = corner_2
    pattern_collection["center_line"] = center_line
    pattern_collection["side_line"] = side_line
    pattern_collection["center_point"] = center_point
    pattern_collection["top_point"] = top_point
    pattern_collection["corner_point"] = corner_point
    pattern_collection["side_line"] = side_line
    pattern_collection["corner_3"] = corner_3
    pattern_collection["anti_corner"] = anti_corner
    pattern_collection["cross"] = cross
    pattern_collection["double_line"] = double_line
    pattern_collection["double_side_point"] = double_side_point

    def __init__(self, patterns, weight=None):
        """
        contain a dict with every pattern selected an their associated weight
        :param patterns: list of patterns desired
        :param weight: list of weight for patterns selected
        """
        # PatternGenerator.pattern_collection = dict of every patterns with default weight of 1
        # self.pattern_selection = dict of selected patterns among the whole set
        self.pattern_selection = list(patterns)
        if weight is not None:
            assert len(weight) == len(patterns)
            for i in range(len(weight)):
                self.pattern_selection[i].append(weight[i])
        else:
            for i in range(len(patterns)):
                self.pattern_selection[i] = [self.pattern_selection[i]]
                self.pattern_selection[i].append(1)

    def assign_weight(self, key, weight):
        for el in self.pattern_selection:
            if el[0] == key:
                el[1] = weight

    def return_random(self):
        chosen_one = rd.choice(self.pattern_selection)[0]
        angle = rd.randint(0, 3) * 90
        return rotation(angle, self.pattern_collection[chosen_one])

    def return_semi_random(self):
        # random float between 0 and 1
        rnd = rd.random()
        # calculating the total sum of the weight then reducing it
        sum_weight = 0
        for el in self.pattern_selection:
            sum_weight += el[1]
        adjusted_sum = sum_weight * rnd

        # find the random pattern
        for item in self.pattern_selection:
            # item[0] = name / item[1] = weight
            adjusted_sum -= item[1]
            if adjusted_sum <= 0:
                angle = rd.randint(0, 3) * 90
                return rotation(angle, self.pattern_collection[item[0]])
        raise ValueError("Problem in function")

    def return_empty(self):
        return self.empty


if __name__ == "__main__":
    my_generator = PatternGenerator(["empty", "double_line", "center_point"])
    print("selection :\n", my_generator.pattern_selection)
    my_generator.assign_weight("empty", 0)
    print("\nassignation of weight:\n", my_generator.pattern_selection)
    print("\nchoice of a random el:\n", my_generator.return_random())
    print("\nchoice of a semi random el:\n", my_generator.return_semi_random())
    print("\nreturn of empty\n:", my_generator.return_empty())