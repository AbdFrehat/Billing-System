import { useEffect, useRef, useState } from "react";

export default function Checkbox({ value, keyValue, onCheck }) {
  const [isChecked, setIsChecked] = useState(false);
  const isInitialRender = useRef(true);
  function onChange() {
    setIsChecked((prev) => {
      return !prev;
    });
  }
  useEffect(() => {
    if (isInitialRender.current) {
      isInitialRender.current = false;
      return;
    }
    onCheck({ key: keyValue, checked: isChecked });
  }, [isChecked, keyValue]);
  let labelClasses =
    "checkbox-label bg-gray-200 w-8 h-8 flex items-center justify-center cursor-pointer";
  if (isChecked) {
    labelClasses += " bg-textColor";
  }
  return (
    <div className="flex justify-center items-center">
      <input
        type="checkbox"
        id={`checkbox-${keyValue}`}
        className="hidden"
        value={value}
        checked={isChecked}
        onChange={onChange}
      />
      <label htmlFor={`checkbox-${keyValue}`} className={labelClasses} />
    </div>
  );
}
