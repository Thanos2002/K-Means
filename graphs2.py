import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv('output_points.csv', header=None, names=['x1', 'x2'])
df2 = pd.read_csv('output_centroids.csv', header=None, names=['c1', 'c2'])

plt.scatter(df['x1'],df['x2'], marker='+', color='blue')
plt.scatter(df2['c1'],df2['c2'], marker='*', color='red')

error = [616.4840881430129,323.2948450458352,219.74229059187357,196.89042793265395]
M_values = [3,6,9,12]

plt.plot(M_values,error, marker='o')
plt.title('Διάγραμμα μεταβολής σφάλματος ομαδοποίησης με τον αριθμό των ομάδων')
plt.xlabel('M Values')
plt.ylabel('Error')
plt.xticks(M_values)
