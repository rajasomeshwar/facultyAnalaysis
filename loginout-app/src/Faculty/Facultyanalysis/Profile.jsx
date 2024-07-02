export default function ProfileComponent()
{
    const today =new Date();
    const targerDate=new Date(today.getFullYear()+12,today.getMonth(),today.getDay());
    const todos = [

        {
            id: 1,
            description: "hahahha",
            done: false,
            targerdate : targerDate
        },
        {
            id: 2,
            description: "Finish the report",
            done: false,
            targerdate : targerDate
        },
        {
            id: 3,
            description: "Call John",
            done: false,
            targerdate : targerDate
        }
    ];

    return (
        <div className="TodoComponent">
            <h2>To-Do List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Description</th>
                        <th>Isdone</th>
                        <th>TargetDate</th>
                    
                    </tr>
                </thead>
                <tbody>
                    {todos.map(todo => (
                        <tr key={todo.id}>
                            <td>{todo.id}</td>
                            <td>{todo.description}</td>
                            <td>{todo.done.toString()}</td>
                            <td>{todo.targerdate.toString()}</td>
                            <td>
                                <button className="update-button">Update</button>
                                <button className="done-button">Done</button>
                                <button className="delete-button">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

// export default TodoComponent;