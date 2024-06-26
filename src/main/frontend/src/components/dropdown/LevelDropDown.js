
export const LevelDropDown = ({value, setLevelIdentify, setIsOpen, isOpen, isLast, handleSelectChange}) => {
    const valueClick = () => {
        setLevelIdentify(value)
        setIsOpen(!isOpen)
        handleSelectChange('level', value)
    }

    const listItemStyle = {
            padding: '10px'
        };

    if (isLast) {
        listItemStyle.borderBottom = 'none';
    } else {
        listItemStyle.borderBottom = '1px solid #ccc';
    }

    return (
        <li onClick={valueClick} style={listItemStyle}>{value}</li>

    )
}

export default LevelDropDown;