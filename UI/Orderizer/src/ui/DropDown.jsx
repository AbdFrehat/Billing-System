export default function DropDown({
  selectStyle = "bg-mainColor p-2 w-full",
  optionStyle = "text-center",
  elements,
  onChange,
}) {
  return (
    <select className={selectStyle} onChange={onChange}>
      {elements.map((element) => (
        <option className={optionStyle} value={element} key={element}>
          {element}
        </option>
      ))}
    </select>
  );
}
