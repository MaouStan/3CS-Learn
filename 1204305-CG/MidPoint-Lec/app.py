from flask import Flask, render_template, request

app = Flask(__name__)

# ฟังก์ชันคำนวณจุดของวงกลม


def midpoint_circle_table(radius, xcenter=0, ycenter=0):
  print(xcenter, ycenter)
  quadrants = {1: [], 2: [], 3: [], 4: [], 5: [], 6: [], 7: [], 8: []}
  x = 0
  y = radius
  p = 1 - radius
  formula = "p<sub>k+1</sub> = 1 - r"
  calculation = f"p<sub>k+1</sub> = 1 - {radius}"

  # จุดเริ่มต้น (K = 0)
  # quadrants[1].append((0, (x + xcenter, y + ycenter), formula + "<p>" +calculation + "</p>", p))

  while x <= y:
    print(x, y)
    # เพิ่มจุดในแต่ละควอดแรนต์ with center translation
    quadrants[1].append((len(quadrants[1]), (x + xcenter, y + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[2].append((len(quadrants[2]), (y + xcenter, x + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[3].append((len(quadrants[3]), (y + xcenter, -x + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[4].append((len(quadrants[4]), (x + xcenter, -y + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[5].append((len(quadrants[5]), (-x + xcenter, -y + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[6].append((len(quadrants[6]), (-y + xcenter, -x + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[7].append((len(quadrants[7]), (-y + xcenter, x + ycenter), formula + "<p>" + calculation + "</p>", p))
    quadrants[8].append((len(quadrants[8]), (-x + xcenter, y + ycenter), formula + "<p>" + calculation + "</p>", p))
    x += 1
    if p < 0:
      formula = "p<sub>k+1</sub> = p<sub>k</sub> + 2x<sub>k+1</sub> + 1"
      calculation = f"p<sub>k+1</sub> = {p} + 2({x+1}) + 1"
      p = p + 2 * x + 1
    else:
      y -= 1
      formula = "p<sub>k+1</sub> = p<sub>k</sub> + 2x<sub>k+1</sub> - 2y<sub>k+1</sub> + 1"
      calculation = f"p<sub>k+1</sub> = {p} + 2({x+1}) - 2({y+1}) + 1"
      p = p + 2 * x - 2 * y + 1

  quadrants = {1: quadrants[1]}
  return quadrants


def midpoint_ellipse_table(rx, ry, xcenter, ycenter):
  quadrants = {1: [], 2: [], 3: [], 4: []}
  x, y = 0, ry
  p1 = (ry**2) - (rx**2 * ry) + ((1/4) * rx**2)
  px = 0
  py = 2 * rx**2 * y
  k = 0

  print(p1, px, py)

  # Region 1
  oldp1 = p1
  while px < py:
    px_formula = "px<sub>k+1</sub> = 2r<sub>y</sub>²x<sub>k+1</sub>"
    py_formula = "py<sub>k+1</sub> = 2r<sub>x</sub>²y<sub>k+1</sub>"
    px_calculation = f"px<sub>k+1</sub> = 2({ry}²)({x}) = {px}"
    py_calculation = f"py<sub>k+1</sub> = 2({rx}²)({y}) = {py}"
    formula = ""
    calculation = ""
    if k == 0:
      formula = f"p1<sub>1</sub> = r<sub>y</sub><sup>2</sup> - r<sub>x</sub><sup>2</sup>r<sub>y</sub> + (1/4)r<sub>x</sub><sup>2</sup>"
      calculation = f"p1<sub>1</sub> = {ry}² - ({rx}²)({ry}) + (1/4)({rx})² = {p1}"
    else:
      if p1 < 0:
        formula = "p1<sub>k+1</sub> = p1<sub>k</sub> + r<sub>y</sub>² + px"
        calculation = f"p1<sub>k+1</sub> = {oldp1} + {ry}² + {px} = {p1}"
      else:
        formula = "p1<sub>k+1</sub> = p1<sub>k</sub> + r<sub>y</sub>² + px - py"
        calculation = f"p1<sub>k+1</sub> = {oldp1} + {ry}² + {px} - {py} = {p1}"

    real_x = x + xcenter
    real_y = y + ycenter
    quadrants[1].append((k, p1, (x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[2].append((k, p1, (-x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[3].append((k, p1, (-x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[4].append((k, p1, (x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    oldp1 = p1
    x += 1
    px += 2 * ry**2
    if p1 < 0:
      p1 += ry**2 + px
    else:
      y -= 1
      py -= 2 * rx**2
      p1 += ry**2 + px - py
    k += 1

  px_formula = "px<sub>k+1</sub> = 2r<sub>y</sub>²x<sub>k+1</sub>"
  py_formula = "py<sub>k+1</sub> = 2r<sub>x</sub>²y<sub>k+1</sub>"
  px_calculation = f"px<sub>k+1</sub> = 2({ry}²)({x}) = {px}"
  py_calculation = f"py<sub>k+1</sub> = 2({rx}²)({y}) = {py}"
  formula = ""
  calculation = ""
  if k == 0:
    formula = f"p1<sub>1</sub> = r<sub>y</sub><sup>2</sup> - r<sub>x</sub><sup>2</sup>r<sub>y</sub> + (1/4)r<sub>x</sub><sup>2</sup>"
    calculation = f"p1<sub>1</sub> = {ry}² - {rx}²({ry}) + (1/4)({rx})² = {p1}"
  else:
    if p1 < 0:
      formula = "p1<sub>k+1</sub> = p1<sub>k</sub> + r<sub>y</sub>² + px"
      calculation = f"p1<sub>k+1</sub> = {oldp1} + {ry}² + {px} = {p1}"
    else:
      formula = "p1<sub>k+1</sub> = p1<sub>k</sub> + r<sub>y</sub>² + px - py"
      calculation = f"p1<sub>k+1</sub> = {oldp1} + {ry}² + {px} - {py} = {p1}"

  real_x = x + xcenter
  real_y = y + ycenter
  quadrants[1].append((k, p1, (x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  quadrants[2].append((k, p1, (-x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  quadrants[3].append((k, p1, (-x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  quadrants[4].append((k, p1, (x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))

  # Region 2
  p2 = (ry**2) * ((x + 0.5)**2) + (rx**2) * ((y - 1)**2) - (rx**2 * ry**2)
  oldp2 = p2
  k = 0
  while y >= 0:
    formula = ""
    calculation = ""
    px_formula = "px<sub>k+1</sub> = 2r<sub>y</sub>²x<sub>k+1</sub>"
    py_formula = "py<sub>k+1</sub> = 2r<sub>x</sub>²y<sub>k+1</sub>"
    px_calculation = f"px<sub>k+1</sub> = 2({ry}²)({x}) = {px}"
    py_calculation = f"py<sub>k+1</sub> = 2({rx}²)({y}) = {py}"
    if k == 0:
      formula = "p2<sub>1</sub> = r<sub>y</sub>²(x + 0.5)² + r<sub>x</sub>²(y - 1)² - r<sub>x</sub>²r<sub>y</sub>²"
      calculation = f"p2<sub>1</sub> = {ry}²({x + 0.5})² + {rx}²({y - 1})² - ({rx}²)({ry}²) = {p2}"
    else:
      if p2 > 0:
        formula = "p2<sub>k+1</sub> = p2<sub>k</sub> + r<sub>x</sub>² - py"
        calculation = f"p2<sub>k+1</sub> = {oldp2} + {rx}² - {py} = {p2}"
      else:
        formula = "p2<sub>k+1</sub> = p2<sub>k</sub> + r<sub>x</sub>² - py + px"
        calculation = f"p2<sub>k+1</sub> = {oldp2} + {rx}² - {py} + {px} = {p2}"

    real_x = x + xcenter
    real_y = y + ycenter
    quadrants[1].append((k, p1, (x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[2].append((k, p1, (-x, y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[3].append((k, p1, (-x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[4].append((k, p1, (x, -y), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    oldp2 = p2
    y -= 1
    py -= 2 * rx**2
    if p2 > 0:
      p2 += rx**2 - py
    else:
      x += 1
      px += 2 * ry**2
      p2 += rx**2 - py + px
    k += 1

  # remove other quadrants except 1
  quadrants = {1: quadrants[1]}
  return quadrants


@app.route('/ellipse', methods=['GET', 'POST'])
def ellipse():
  result = None
  if request.method == 'POST':
    rx = int(request.form['rx'])
    ry = int(request.form['ry'])
    xcenter = int(request.form['xcenter'])
    ycenter = int(request.form['ycenter'])
    result = midpoint_ellipse_table(rx, ry, xcenter, ycenter)
  return render_template('ellipse.html', result=result)


@app.route('/', methods=['GET', 'POST'])
def index():
  result = None
  if request.method == 'POST':
    radius = int(request.form['radius'])
    xcenter = int(request.form.get('xcenter', 0))
    ycenter = int(request.form.get('ycenter', 0))
    result = midpoint_circle_table(radius, xcenter, ycenter)
  return render_template('index.html', result=result)


if __name__ == '__main__':
  app.run(debug=True)
