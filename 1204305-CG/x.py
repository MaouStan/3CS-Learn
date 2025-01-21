import numpy as np
import matplotlib.pyplot as plt


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
    quadrants[1].append((k, p1, (x+ xcenter, y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[2].append((k, p1, (-x+ xcenter+ xcenter, y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[3].append((k, p1, (-x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[4].append((k, p1, (x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
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
  # quadrants[1].append((k, p1, (x+ xcenter, y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  # quadrants[2].append((k, p1, (-x+ xcenter, y+ ycenter+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  # quadrants[3].append((k, p1, (-x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
  # quadrants[4].append((k, p1, (x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))

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
    quadrants[1].append((k, p1, (x + xcenter, y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[2].append((k, p1, (-x+ xcenter, y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[3].append((k, p1, (-x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
    quadrants[4].append((k, p1, (x+ xcenter, -y+ ycenter), px_formula + "<p>" + px_calculation + "</p>", py_formula + "<p>" + py_calculation + "</p>", formula + "<p>" + calculation + "</p>"))
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

  return quadrants


def plot_ellipse(rx, ry, xcenter, ycenter):
  rx = 11
  ry = 20
  xcenter = 2
  ycenter = 7
  # Get points from the midpoint algorithm
  quadrants = midpoint_ellipse_table(rx, ry, xcenter, ycenter)

  # Create a new figure
  plt.figure(figsize=(10, 10))

  dy = max(ry, ycenter)


  # Plot upper half (quadrants 1 and 2) in red
  for q in [quadrants[1], quadrants[2]]:
    x_coords = [int(point[2][0]) for point in q]
    y_coords = [int(point[2][1] - dy - 2) for point in q]
    print(x_coords, y_coords)
    plt.plot(x_coords, y_coords, 'ro', markersize=3)
    # connect line draw
    for i in range(len(x_coords)-1):
      plt.plot([x_coords[i], x_coords[i+1]], [y_coords[i], y_coords[i+1]], 'r-')

  # Plot lower half (quadrants 3 and 4) in blue
  for q in [quadrants[3], quadrants[4]]:
    x_coords = [int(point[2][0]) for point in q]
    y_coords = [int(point[2][1] + dy + 2) for point in q]
    print(x_coords, y_coords)
    plt.plot(x_coords, y_coords, 'bo', markersize=3)
    # connect line draw
    for i in range(len(x_coords)-1):
      plt.plot([x_coords[i], x_coords[i+1]], [y_coords[i], y_coords[i+1]], 'b-')

  # plot center
  plt.plot(xcenter, ycenter, 'go', markersize=3)

  # Set equal aspect ratio and grid
  plt.axis('equal')
  # tick as integer
  plt.locator_params(axis='x', nbins=10)
  plt.locator_params(axis='y', nbins=10)
  plt.grid(True)
  plt.title(f'Ellipse (rx={rx}, ry={ry}, center=({xcenter},{ycenter}))')
  plt.show()

# Example usage:
plot_ellipse(11, 20, 2, 7)  # Draw an ellipse with rx=100, ry=60, centered at origin
