import { useState } from "react";
import Checkbox from "../../ui/Checkbox";
import DeleteButton from "../../ui/DeleteButton";
import DropDown from "../../ui/DropDown";
import Table from "../../ui/Table";
import UpdateButton from "../../ui/UpdateButton";

export default function Authorities({ authorities }) {
  const groups = Object.keys(authorities);
  const [group, setGroup] = useState(groups[0]);
  const [checkedAuthorities, setCheckedAuthorities] = useState([]);
  const groupAuthorities = authorities[group];

  function onChange(event) {
    setGroup(event.target.value);
    setCheckedAuthorities((prev) => []);
  }

  function onCheck(status) {
    setCheckedAuthorities((prev) => {
      if (status.checked) {
        return [...prev, groupAuthorities[status.key]];
      } else {
        let prevCheckedAuthorities = [...prev];
        prevCheckedAuthorities.splice(status.key, 1);
        return prevCheckedAuthorities;
      }
    });
  }

  function onDeleteRow(authorityName) {
    console.log("Delete" + authorityName);
  }

  function onUpdateRow(authorityName) {
    console.log("Update" + authorityName);
  }

  let rows = groupAuthorities.map((authority, index) => [
    <Checkbox
      key={`${group}-${authority}-${index}`}
      keyValue={index}
      onCheck={onCheck}
    />,
    authority.name,
    <div className="flex justify-center">
      <UpdateButton
        onClick={() => onUpdateRow(authority.name)}
        className="w-10 cursor-pointer mr-2"
      />
      <DeleteButton
        onClick={() => onDeleteRow(authority.name)}
        className="w-10 cursor-pointer"
      />
    </div>,
  ]);
  let data = [["", "Authority Name", "Actions"], ...rows];
  return (
    <div className="flex flex-col items-center w-full mt-10 justify-center">
      <div className="mb-4 w-1/2 flex">
        <DropDown elements={groups} onChange={onChange} />
        <div className="flex ml-10">
          <UpdateButton className="w-10 cursor-pointer mr-2" />
          <DeleteButton className="w-10 cursor-pointer" />
        </div>
      </div>

      <Table data={data} />
    </div>
  );
}
