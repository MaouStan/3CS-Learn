from flask import Flask, render_template, request

app = Flask(__name__)

# ฟังก์ชันคำนวณจุดของวงกลม
def midpoint_circle_table(radius):
    quadrants = {1: [], 2: [], 3: [], 4: [], 5: [], 6: [], 7: [], 8: []}
    x = 0
    y = radius
    p = 1 - radius
    formula = "p_k = 1 - r"
    calculation = f"p_k = 1 - {radius}"

    # จุดเริ่มต้น (K = 0)
    quadrants[1].append((0, (x, y), formula, calculation, p))

    while x < y:
        x += 1
        if p < 0:
            formula = "p_k+1 = p_k + 2x_k+1 + 1"
            calculation = f"p_k+1 = {p} + 2*{x} + 1"
            p = p + 2 * x + 1
        else:
            y -= 1
            formula = "p_k+1 = p_k + 2x_k+1 + 1 - 2y_k+1"
            calculation = f"p_k+1 = {p} + 2*{x} + 1 - 2*{y}"
            p = p + 2 * x - 2 * y + 1

        # เพิ่มจุดในแต่ละควอดแรนต์
        quadrants[1].append((len(quadrants[1]), (x, y), formula, calculation, p))
        quadrants[2].append((len(quadrants[2]), (y, x), formula, calculation, p))
        quadrants[3].append((len(quadrants[3]), (y, -x), formula, calculation, p))
        quadrants[4].append((len(quadrants[4]), (x, -y), formula, calculation, p))
        quadrants[5].append((len(quadrants[5]), (-x, -y), formula, calculation, p))
        quadrants[6].append((len(quadrants[6]), (-y, -x), formula, calculation, p))
        quadrants[7].append((len(quadrants[7]), (-y, x), formula, calculation, p))
        quadrants[8].append((len(quadrants[8]), (-x, y), formula, calculation, p))

    return quadrants


def midpoint_ellipse_table(rx, ry):
    quadrants = {1: [], 2: [], 3: [], 4: []}
    x, y = 0, ry
    p1 = (ry**2) - (rx**2 * ry) + (0.25 * rx**2)
    px = 0
    py = 2 * rx**2 * y
    k = 0

    # Region 1
    while px < py:
        # บันทึกผลลัพธ์ในแต่ละควอแดรนต์
        quadrants[1].append((k, p1, (x, y), px, py))
        quadrants[2].append((k, p1, (-x, y), px, py))
        quadrants[3].append((k, p1, (-x, -y), px, py))
        quadrants[4].append((k, p1, (x, -y), px, py))
        x += 1
        px += 2 * ry**2
        if p1 < 0:
            p1 += ry**2 + px
        else:
            y -= 1
            py -= 2 * rx**2
            p1 += ry**2 + px - py
        k += 1

    # Region 2
    p2 = (ry**2) * ((x + 0.5)**2) + (rx**2) * ((y - 1)**2) - (rx**2 * ry**2)
    while y >= 0:
        quadrants[1].append((k, p2, (x, y), px, py))
        quadrants[2].append((k, p2, (-x, y), px, py))
        quadrants[3].append((k, p2, (-x, -y), px, py))
        quadrants[4].append((k, p2, (x, -y), px, py))
        y -= 1
        py -= 2 * rx**2
        if p2 > 0:
            p2 += rx**2 - py
        else:
            x += 1
            px += 2 * ry**2
            p2 += rx**2 - py + px
        k += 1

    return quadrants


@app.route('/ellipse', methods=['GET', 'POST'])
def ellipse():
    result = None
    if request.method == 'POST':
        rx = int(request.form['rx'])
        ry = int(request.form['ry'])
        result = midpoint_ellipse_table(rx, ry)
    return render_template('ellipse.html', result=result)

@app.route('/', methods=['GET', 'POST'])
def index():
    result = None
    if request.method == 'POST':
        radius = int(request.form['radius'])
        result = midpoint_circle_table(radius)
    return render_template('index.html', result=result)


if __name__ == '__main__':
    app.run(debug=True)
