import csv, sys, json

def main():
    if len(sys.argv) != 3:
        print('usage: a.out <in-file> <out-file>')
        return

    result = {'items': []}

    with open(sys.argv[1]) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        width = 0
        height = 0
        for height, row in enumerate(csv_reader):
            width = max(width, len(row))

            for x in range(0, len(row)):
                result['items'].append({
                    'x': x,
                    'y': height,
                    'type': row[x]
                })

            height += 1

    result['width'] = width
    result['height'] = height

    with open(sys.argv[2], 'w') as out_file:
        out_file.write(json.dumps(result, indent=2))


if __name__ == '__main__':
    main()
