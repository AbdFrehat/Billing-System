/*
{ data: [
    [Authority Name, Actions],
    [x, actions],
    ]
}

*/

export default function Table({ data }) {
  return (
    <table className="min-w-full divide-y divide-gray-200">
      <thead>
        <tr>
          {data[0].map((header) => (
            <th
              key={header}
              className="px-6 py-3 bg-mainColor border-2 border-textColor text-center uppercase tracking-wider"
            >
              {header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody className="bg-white divide-y divide-gray-200">
        {data.slice(1).map((row, rowIndex) => {
          return (
            <tr key={rowIndex}>
              {row.map((col, colIndex) => {
                return (
                  <td
                    key={colIndex}
                    className="px-6 py-4 text-center border-2 border-textColor whitespace-nowrap bg-mainColor"
                  >
                    {col}
                  </td>
                );
              })}
            </tr>
          );
        })}
      </tbody>
    </table>
  );
}
