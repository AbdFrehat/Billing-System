import { useState } from "react";
import DeleteButton from "../../ui/DeleteButton";
import DropDown from "../../ui/DropDown";
import UpdateButton from "../../ui/UpdateButton";

export default function Authorities({ authorities }) {
  const groups = Object.keys(authorities);
  const [group, setGroup] = useState(groups[0]);
  function onChange(event) {
    setGroup(event.target.value);
    console.log(authorities[group]);
  }
  return (
    <div className="flex flex-col items-center w-full mt-10 justify-center">
      <div className="mb-4 w-1/2">
        <DropDown elements={groups} onChange={onChange} />
      </div>
      <table className="min-w-full divide-y divide-gray-200">
        <thead>
          <tr>
            <th className="px-6 py-3 bg-mainColor text-center uppercase tracking-wider">
              Authority Name
            </th>
            <th className="px-6 py-3 bg-mainColor text-center uppercase tracking-wider">
              Actions
            </th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {authorities[group].map((authority) => {
            return (
              <tr key={authority.name}>
                <td className="px-6 py-4 text-center whitespace-nowrap bg-mainColor">
                  {authority.name}
                </td>
                <td className="px-6 py-4 whitespace-nowrap bg-mainColor flex justify-center">
                  <UpdateButton className="w-10 cursor-pointer mr-2" />
                  <DeleteButton className="w-10 cursor-pointer" />
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
